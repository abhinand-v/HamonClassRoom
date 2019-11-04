package tech.codewithabhi.hamonclassroom.ui.home.student

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.student_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import tech.codewithabhi.hamonclassroom.R
import tech.codewithabhi.hamonclassroom.data.network.models.Student

class StudentFragment : Fragment(), KodeinAware, StudentFragmentListener {

    override val kodein by kodein()
    private val factory by instance<StudentViewModelFactory>()

    private lateinit var viewModel: StudentViewModel
    private lateinit var appContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        context?.let { appContext = it }
        return inflater.inflate(R.layout.student_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(StudentViewModel::class.java)
        viewModel.listener = this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getStudentList().observe(this, Observer {
            if (it.isNotEmpty()) {
                initRecyclerView(it)
            }
        })
    }

    private fun initRecyclerView(students: List<Student>) {

        val studentListItems = students.map {
            StudentListItem(it)
        }

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(studentListItems)
        }

        recyclerView_studentList.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    override fun startStopRefresh(state: Boolean) {
        swipeRefresh_student_list?.isRefreshing = state
    }

}
