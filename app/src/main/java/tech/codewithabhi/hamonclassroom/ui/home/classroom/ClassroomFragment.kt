package tech.codewithabhi.hamonclassroom.ui.home.classroom

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.codewithabhi.hamonclassroom.R

class ClassroomFragment : Fragment() {

    companion object {
        fun newInstance() = ClassroomFragment()
    }

    private lateinit var viewModel: ClassroomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.classroom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ClassroomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
