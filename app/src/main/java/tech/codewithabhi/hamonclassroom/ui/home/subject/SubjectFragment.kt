package tech.codewithabhi.hamonclassroom.ui.home.subject

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
import kotlinx.android.synthetic.main.subject_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import tech.codewithabhi.hamonclassroom.R
import tech.codewithabhi.hamonclassroom.data.network.models.Subject
import tech.codewithabhi.hamonclassroom.databinding.DialogLayoutSubjectDetailsBinding

class SubjectFragment : Fragment(), KodeinAware, SubjectFragmentListener {

    override val kodein by kodein()
    private val factory by instance<SubjectViewModelFactory>()

    private lateinit var viewModel: SubjectViewModel
    private lateinit var appContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let { appContext = it }
        return inflater.inflate(R.layout.subject_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(SubjectViewModel::class.java)
        viewModel.listener = this
    }

    override fun onResume() {
        super.onResume()

        viewModel.getList().observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                initRecyclerView(it)
            }
        })

        swipeRefresh_subject_list.setOnRefreshListener {
            viewModel.getList()
        }
    }

    private fun initRecyclerView(subjects: List<Subject>) {

        val bindedListItems = subjects.map {
            ListItem(it)
        }

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(bindedListItems)
            setOnItemClickListener { item, _ ->
                showDetailDialog(subjects[this.getAdapterPosition(item)])
            }
        }

        recyclerView_subject_list.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun showDetailDialog(subject: Subject) {

        val viewBinding = DataBindingUtil.inflate<DialogLayoutSubjectDetailsBinding>(
            layoutInflater,
            R.layout.dialog_layout_subject_details,
            null, false
        )

        viewBinding.subject = subject

        val dialog = MaterialAlertDialogBuilder(appContext).apply {
            setView(viewBinding.root)
            setNegativeButton("close") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }

        dialog.show()
    }

    override fun startStopRefresh(state: Boolean) {
        swipeRefresh_subject_list.isRefreshing = state
    }
}
