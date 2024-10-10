package com.binet.G3b8qZe9Wk2.ui.adapter_delegates

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.binet.G3b8qZe9Wk2.R
import com.binet.G3b8qZe9Wk2.domain.model.CategoryType
import com.binet.G3b8qZe9Wk2.domain.model.Medication
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

data class HomeItem(
    val id: Int,
    val categoryType: CategoryType
) : RecyclerItem


fun Medication.toHomeItem() = HomeItem(id, categoryType)

fun homeItemAdapterDelegate(onItemClick: (HomeItem) -> Unit) =
    adapterDelegate<HomeItem, RecyclerItem>(
        R.layout.home_item
    ) {
        itemView.setOnClickListener {
            onItemClick(item)
        }

        val title = findViewById<TextView>(R.id.headline)
        val body = findViewById<TextView>(R.id.body)
        val image = findViewById<AppCompatImageView>(R.id.image)

        bind {
            title.text = when (item.categoryType) {
                CategoryType.Insecticides -> context.getString(R.string.insecticides_title)
                CategoryType.DressingAgent -> context.getString(R.string.dressing_agent_title)
            }
            body.text = when (item.categoryType) {
                CategoryType.Insecticides -> context.getString(R.string.Insecticides_desc)
                CategoryType.DressingAgent -> context.getString(R.string.dressing_agent_desc)
            }
            image.setImageResource(
                when (item.categoryType) {
                    CategoryType.Insecticides -> R.drawable.insecticides
                    CategoryType.DressingAgent -> R.drawable.dressing_agent
                }
            )
        }
    }