package tech.codewithabhi.hamonclassroom.ui.home.subject

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import tech.codewithabhi.hamonclassroom.R

class SubjectFragment : Fragment() {

    companion object {
        fun newInstance() = SubjectFragment()
    }

    private lateinit var viewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subject_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
