package com.sarrawi.msgs

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sarrawi.msgs.adapter.MsgsTypes_Adapter
import com.sarrawi.msgs.api.ApiService
import com.sarrawi.msgs.databinding.ActivityMainBinding
import com.sarrawi.msgs.db.LocaleSource
import com.sarrawi.msgs.repository.MsgsTypesRepo
import com.sarrawi.msgs.viewModel.MsgsTypesViewModel
import com.sarrawi.msgs.viewModel.MyViewModelFactory


//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//aaaaaaa
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MsgsTypesViewModel
    private lateinit var msgstypesAdapter: MsgsTypes_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = ApiService.provideRetrofitInstance()
        val mainRepository = MsgsTypesRepo(retrofitService, LocaleSource(this))
      //  supportActionBar?.hide()

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MsgsTypesViewModel::class.java)
        setUpRv()

    }

    private fun setUpRv() {

        msgstypesAdapter = MsgsTypes_Adapter()
        binding.recyclerView.apply {
            adapter = msgstypesAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.VERTICAL,
                false
            )

            setHasFixedSize(true)
        }

        viewModel.responseMsgsTypes.observe(this) { listTvShows ->
            msgstypesAdapter.msgsTypesModel = listTvShows
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.setting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                viewModel.refreshPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}