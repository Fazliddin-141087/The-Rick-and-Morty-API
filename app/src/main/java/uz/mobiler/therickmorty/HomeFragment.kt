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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

  private  fun loadData(){
      appDatabase = AppDatabase.getAppDatabase(requireContext())
      list = ArrayList()
      list=appDatabase.resultDao().getAllResult() as ArrayList

      Log.d(TAG, "onCreateView: ${list.size}")

      rvAdapters = RvAdapters(list,object :RvAdapters.MyOnClickListener{
          override fun onMyItemClick(result: Result, id: Int) {
              var bundle=Bundle()
              bundle.putSerializable("result",result)
              bundle.putInt("id",result.id!!)
              findNavController().navigate(R.id.resultFragment,bundle)
          }
      })
      binding.rv.adapter = rvAdapters

  }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}