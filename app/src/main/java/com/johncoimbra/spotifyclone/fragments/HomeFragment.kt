package com.johncoimbra.spotifyclone.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.johncoimbra.spotifyclone.R
import com.johncoimbra.spotifyclone.databinding.AlbumItemBinding
import com.johncoimbra.spotifyclone.databinding.CategoryItemBinding
import com.johncoimbra.spotifyclone.databinding.FragmentHomeBinding
import com.johncoimbra.spotifyclone.model.*
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindingHome = FragmentHomeBinding.bind(view)
        //bindingFragmentHome = bindingHome

        val categories = arrayListOf<Category>()

        categoryAdapter = CategoryAdapter(categories)
        bindingHome.recyclerViewCategories.adapter = categoryAdapter
        bindingHome.recyclerViewCategories.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotifyAPI::class.java)
            .ListCategories()
            .enqueue(object : Callback<Categories>{
                override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            categoryAdapter.categories.clear()
                            categoryAdapter.categories.addAll(it.categories)
                            categoryAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onFailure(call: Call<Categories>, t: Throwable) {
                    Toast.makeText(context, "Erro na chamado do servidor", Toast.LENGTH_SHORT).show()
                }

            })
        

    }

    //---------------------------------Category Adapter-------------------------------------------//

    private inner class CategoryAdapter(internal val categories: MutableList<Category>) : RecyclerView.Adapter<CategoryHolder>() {

        private lateinit var binding: CategoryItemBinding //viewBinding na CategoryAdapter

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CategoryHolder(binding)
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            val category = categories[position]
            holder.bind(category)
        }

        override fun getItemCount(): Int = categories.size
    }

    private inner class CategoryHolder(binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val mTextTitleCategory = binding.textTitleCategory
        private val mRecylerAlbums = binding.recyclerAlbums
        fun bind(category: Category) {
            mTextTitleCategory.text = category.title
            mRecylerAlbums.adapter = AlbumsAdapter(category.albums)
            mRecylerAlbums.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        }
    }
    //---------------------------------Albums Adapter--------------------------------------------//

    private inner class AlbumsAdapter(private val albums: List<Album>): RecyclerView.Adapter<AlbumsHolder>() {
        private lateinit var binding: AlbumItemBinding //viewBinding na AlbumsAdapter
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder {
            binding = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AlbumsHolder(binding)
        }

        override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
            val album = albums[position]
            holder.bind(album)
        }

        override fun getItemCount(): Int = albums.size
    }

    private inner class AlbumsHolder(binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root){
        private val mImageAlbum = binding.imageAlbum
        fun bind(album: Album) {
            Picasso.get()
                .load(album.album)
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(mImageAlbum)
        }
    }
}