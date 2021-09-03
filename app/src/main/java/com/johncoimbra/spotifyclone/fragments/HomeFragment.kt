package com.johncoimbra.spotifyclone.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.spotifyclone.R
import com.johncoimbra.spotifyclone.databinding.CategoryItemBinding
import com.johncoimbra.spotifyclone.databinding.FragmentHomeBinding
import com.johncoimbra.spotifyclone.model.Category

class HomeFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter
    //private var bindingFragmentHome: FragmentHomeBinding? = null

    companion object {
        fun newInstance(): HomeFragment {
            val fragmentHome = HomeFragment()
            val arguments = Bundle()
            fragmentHome.arguments = arguments
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bindingHome = FragmentHomeBinding.bind(view)
        //bindingFragmentHome = bindingHome

        val categories: MutableList<Category> = ArrayList()
        for (mCategory in 0..4) {
            val category = Category()
            category.title = "Categoria$mCategory"
            categories.add(category)
        }

        categoryAdapter = CategoryAdapter(categories)
        bindingHome.recyclerViewCategories.adapter = categoryAdapter
        bindingHome.recyclerViewCategories.layoutManager = LinearLayoutManager(context)

    }

    private inner class CategoryAdapter(private val categories: MutableList<Category>) :
        RecyclerView.Adapter<CategoryHolder>() {
        //viewBinding na CategoryAdapter
        private lateinit var binding: CategoryItemBinding

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            binding =
                CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryHolder(binding)
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category = categories[position]
            holder.bind(category)
        }

        override fun getItemCount(): Int = categories.size
    }

    private inner class CategoryHolder(binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val mTextTituloCategory = binding.textTitleCategory

        fun bind(category: Category) {
            mTextTituloCategory.text = category.title

        }
    }
}