package uz.mobiler.therickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import uz.mobiler.therickmorty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }
}