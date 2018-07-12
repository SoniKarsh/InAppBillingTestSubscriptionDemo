package com.example.karshsoni.inapppurchasenewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.billingclient.api.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PurchasesUpdatedListener {

    lateinit private var billingClient: BillingClient
    val TAG = "MainActivity"

    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billingClient=BillingClient.newBuilder(this).setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener{
            override fun onBillingServiceDisconnected() {

            }

            override fun onBillingSetupFinished(responseCode: Int) {
                Log.d(TAG, "onBillingSetupFinished: "+responseCode);
            }

        })

        btnAdd.setOnClickListener{
            val flowParams = BillingFlowParams.newBuilder()
                    .setSku("android.test.purchased")
                    .setType(BillingClient.SkuType.SUBS)
                    .build()
            val responseCode = billingClient.launchBillingFlow(this@MainActivity, flowParams)
            Log.d(TAG, "onCreate: $responseCode")
        }

    }
}
