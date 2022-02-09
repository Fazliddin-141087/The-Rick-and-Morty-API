package uz.mobiler.therickmorty

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.mobiler.therickmorty.adapters.RvAdapters
import uz.mobiler.therickmorty.database.AppDatabase
import uz.mobiler.therickmorty.databinding.FragmentHomeBinding
import uz.mobiler.therickmorty.models.Result
import uz.mobiler.therickmorty.viewmodels.AppViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var rvAdapters: RvAdapters
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Result>
    private val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel = ViewModelProvider(this)[AppViewModel::class.java]
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        loadData()

        homeViewModel.getResult().observe(viewLifecycleOwner) {
            binding.rv.visibility = View.VISIBLE
            binding.progress.visibility = View.INVISIBLE
            if (appDatabase.resultDao().getAllResult().isEmpty()) {
                for (result in it.results) {
                    appDatabase.resultDao().insertResult(result)
                    list.add(result)
                    Log.d(TAG, "onCreateView: ${result.name}")
                }
            }
            rvAdapters.notifyDataSetChanged()
        }

        return binding.root
    }

    private fun loadData() {
        appDatabase = AppDatabase.getAppDatabase(requireContext())
        list = ArrayList()
        list = appDatabase.resultDao().getAllResult() as ArrayList

        rvAdapters = RvAdapters(list, object : RvAdapters.MyOnClickListener {
            override fun onMyItemClick(id: Int) {
                var bundle = Bundle()
                bundle.putInt("id",id)
                findNavController().navigate(R.id.resultFragment, bundle)
            }
        })
        binding.rv.adapter = rvAdapters

    }

}