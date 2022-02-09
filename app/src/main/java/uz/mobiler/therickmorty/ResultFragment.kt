package uz.mobiler.therickmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import uz.mobiler.therickmorty.database.AppDatabase
import uz.mobiler.therickmorty.databinding.FragmentResultBinding
import uz.mobiler.therickmorty.models.Result

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
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
    lateinit var binding: FragmentResultBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Result>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentResultBinding.inflate(inflater,container,false)

        appDatabase= AppDatabase.getAppDatabase(requireContext())
        list= ArrayList()

        val id = arguments?.getInt("id")
        val result = id?.let { appDatabase.resultDao().getResultById(it) }

        if (result!=null){
            Picasso.get().load(result.image).into(binding.img)
            binding.tv1.text="Name: ${result.name}"
            binding.tv2.text="Status: ${result.status}"
            binding.tv3.text="Gender: ${result.gender}"
            binding.tv4.text="Url: ${result.url}"
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}