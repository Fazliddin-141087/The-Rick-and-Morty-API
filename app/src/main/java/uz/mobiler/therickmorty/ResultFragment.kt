package uz.mobiler.therickmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import uz.mobiler.therickmorty.database.AppDatabase
import uz.mobiler.therickmorty.databinding.FragmentResultBinding
import uz.mobiler.therickmorty.models.Result

class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<Result>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)

        appDatabase = AppDatabase.getAppDatabase(requireContext())
        list = ArrayList()

        val id = arguments?.getInt("id")
        val result = id?.let { appDatabase.resultDao().getResultById(it) }

        if (result != null) {
            Picasso.get().load(result.image).into(binding.img)
            binding.tv1.text = "Name: ${result.name}"
            binding.tv2.text = "Status: ${result.status}"
            binding.tv3.text = "Gender: ${result.gender}"
            binding.tv4.text = "Url: ${result.url}"
        }

        return binding.root
    }
}