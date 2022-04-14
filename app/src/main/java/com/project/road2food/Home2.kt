package com.project.road2food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home2.*
import kotlinx.android.synthetic.main.activity_home2.view.*

class Home2 : AppCompatActivity(), ItemAdapter.ClickedItem {


    var itemListModal = arrayOf(
        ItemModal(R.drawable.lunch1, "lunch", "lunch desc"),
        ItemModal(R.drawable.lunch2, "Dinner", "dinner desc"),
        ItemModal(R.drawable.lunch3, "Breakfast", "Breakfast desc"),
        ItemModal(R.drawable.lunch4, "Student meal", "student meal desc"),
        ItemModal(R.drawable.lunch5, "Snacks", "Snaks desc")

    )
    var itemModalList = ArrayList<ItemModal>()
    var itemAdapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)

//


        for (item in itemListModal) {
            itemModalList.add(item)
        }
        // set recyclerview to display item in grid layout
        recyclerview.layoutManager = GridLayoutManager(this@Home2,2, )

        recyclerview.setHasFixedSize(true)

        itemAdapter = ItemAdapter(this)
        itemAdapter!!.setData(itemModalList)
        recyclerview.adapter = itemAdapter
    }

    override fun ClickedItem(itemModal: ItemModal) {
        var itemModal1 = itemModal
        var name = itemModal1.name

        when (name) {
            "lunch" ->
                startActivity(Intent(this@Home2, Dinner::class.java).putExtra("data", itemModal1))
            "Dinner" ->
                startActivity(Intent(this@Home2, Dinner::class.java).putExtra("data", itemModal1))
            "Breakfast" ->
                startActivity(Intent(this@Home2, Dinner::class.java).putExtra("data", itemModal1))
            "Student meal" ->
                startActivity(Intent(this@Home2, Dinner::class.java).putExtra("data", itemModal1))
            else -> {
                Toast.makeText(this@Home2, "No action", Toast.LENGTH_LONG).show()
            }
//        Log.e("TAG","====>"+itemModal1.name)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val menuItem = menu!!.findItem(R.id.searchview)
        val searchView = menuItem.actionView as SearchView


        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
              itemAdapter!!.filter.filter(p0)
                return true
            }

        } )
        return true
    }
    private fun searchinlist(){


    }



}