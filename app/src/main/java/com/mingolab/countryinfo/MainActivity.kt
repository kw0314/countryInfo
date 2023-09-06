package com.mingolab.countryinfo

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mingolab.countryinfo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var countryInfoAdapter: CountryInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        retrofitWork()

        binding.btnGet.setOnClickListener{
            retrofitWork()
        }
    }

//    val exceptionHandler = CoroutineExceptionHandler{_,exception ->
//        Log.d("TAG", "CoroutineExceptionHandler:  $exception")
//        binding.contentLoading.hide()
//    }

    @Suppress("UNREACHABLE_CODE")
    private fun retrofitWork() {

        binding.contentLoading.show()
        binding.noInternetConnection.setVisibility(TextView.INVISIBLE)


        val service = RetrofitApi.countryInfoSvc

        var isConnected = NetworkManager.checkNetworkState(applicationContext)
        if (!isConnected) {
            binding.contentLoading.hide()
            binding.noInternetConnection.setVisibility(TextView.VISIBLE)
            return
        }

        val job = CoroutineScope(Dispatchers.IO).launch {
//            throw IllegalArgumentException()


            val response = service.getCountryDataCoroutine()

            if (response.isSuccessful) {
                val result = response.body()
                result?.let {
                    countryInfoAdapter = CountryInfoAdapter(result)

                    withContext(Dispatchers.Main) {
                        binding.contentLoading.hide()
                        binding.recyclerView.apply {
                            layoutManager = LinearLayoutManager(
                                context,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                            adapter = countryInfoAdapter
                            addItemDecoration(
                                DividerItemDecoration(
                                    context,
                                    DividerItemDecoration.VERTICAL
                                )
                            )
                        }
                    }
                }
            } else {
                Toast.makeText(applicationContext, R.string.network_error, Toast.LENGTH_SHORT)
            }

        }
    }
}

