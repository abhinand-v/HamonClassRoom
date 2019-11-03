package tech.codewithabhi.hamonclassroom.ui.home.student

import com.xwray.groupie.databinding.BindableItem
import tech.codewithabhi.hamonclassroom.R
import tech.codewithabhi.hamonclassroom.data.network.models.Student
import tech.codewithabhi.hamonclassroom.databinding.ItemStudentBinding

class StudentListItem(
    private val student: Student
) : BindableItem<ItemStudentBinding>() {

    override fun getLayout() = R.layout.item_student

    override fun bind(viewBinding: ItemStudentBinding, position: Int) {
        viewBinding.student = student
    }

}