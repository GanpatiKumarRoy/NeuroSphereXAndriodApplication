package com.neurospherex.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neurospherex.data.remote.models.Course
import com.neurospherex.databinding.LeaningCourseItemsBinding

class CourseAdapter(private val courseList: List<Course>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(private val binding: LeaningCourseItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.courseIcon.setImageResource(course.courseIcon)
            binding.courseName.text = course.courseName
            binding.courseDescription.text = course.courseDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LeaningCourseItemsBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}