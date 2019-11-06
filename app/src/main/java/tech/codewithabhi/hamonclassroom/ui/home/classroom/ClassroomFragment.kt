package tech.codewithabhi.hamonclassroom.ui.home.classroom

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.classroom_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import tech.codewithabhi.hamonclassroom.R
import tech.codewithabhi.hamonclassroom.data.network.models.Classroom
import tech.codewithabhi.hamonclassroom.databinding.DialogLayoutClassroomDetailsBinding
import tech.codewithabhi.hamonclassroom.util.hideView
import tech.codewithabhi.hamonclassroom.util.showToast
import tech.codewithabhi.hamonclassroom.util.showView

class ClassroomFragment : Fragment(), KodeinAware, ClassroomFragmentListener {

    override val kodein by kodein()
    private val factory by instance<ClassroomViewModelFactory>()

    private lateinit var viewModel: ClassroomViewModel
    private lateinit var appContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let { appContext = it }
        return inflater.inflate(R.layout.classroom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(ClassroomViewModel::class.java)
        viewModel.listener = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                initRecyclerView(it)
            }
        })

        swipeRefresh_clsr_list.setOnRefreshListener {
            viewModel.getList()
        }
    }

    private fun initRecyclerView(classrooms: List<Classroom>) {

        val bindedListItems = classrooms.map {
            ListItem(it)
        }

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(bindedListItems)
            setOnItemClickListener { item, _ ->
                showDetailDialog(classrooms[this.getAdapterPosition(item)])
            }
        }

        recyclerView_clsr_list.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun showDetailDialog(classroom: Classroom) {

        val viewBinding = DataBindingUtil.inflate<DialogLayoutClassroomDetailsBinding>(
            layoutInflater,
            R.layout.dialog_layout_classroom_details,
            null, false
        )


        viewModel.getClassroomDetails(classroom).observe(this, Observer { newClassroom ->

            viewBinding.classroom = newClassroom

            if (newClassroom.subject.isEmpty()) {
                viewBinding.buttonClsrAssignSub.showView()
                viewBinding.boxClsrSub.hideView()
            }

            val dialog = MaterialAlertDialogBuilder(appContext).apply {
                setView(viewBinding.root)
                setNegativeButton("close") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
            }

            val cDialog = dialog.create()
            cDialog.show()

            viewBinding.buttonClsrAssignSub.setOnClickListener {
                dialogAssignSubject(newClassroom)
                cDialog.dismiss()
            }

            viewBinding.buttonClsrChangeSub.setOnClickListener {
                dialogAssignSubject(newClassroom)
                cDialog.dismiss()
            }


        })

    }

    private fun dialogAssignSubject(classroom: Classroom) {
        viewModel.getSubjectRawList().observe(this, Observer { subjects ->

            var checkedItem =
                if (classroom.subject.isNotEmpty() && subjects.contains(classroom.subject))
                    subjects.indexOf(classroom.subject)
                else
                    0

            val dialog = MaterialAlertDialogBuilder(appContext).apply {
                setTitle("Select Subject")

                setSingleChoiceItems(subjects.toTypedArray(), checkedItem) { _, i ->
                    checkedItem = i
                }
                setPositiveButton("select") { dialogInterface, i ->
                    dialogInterface.dismiss()
                    viewModel.assignSubjectToClassroom(classroom, checkedItem)
                    appContext.showToast("Assigned ${subjects[checkedItem]} to ${classroom.name}")
                }
                setNegativeButton("cancel") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
            }
            dialog.show()
        })
    }

    override fun startStopRefresh(state: Boolean) {
        swipeRefresh_clsr_list.isRefreshing = state
    }

}
