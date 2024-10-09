package com.binet.G3b8qZe9Wk2.ui.adapter_delegates

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import coil3.load
import com.binet.G3b8qZe9Wk2.R
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

data class HomeItem(
    val id: Int,
    val title: String,
    val desc: String,
    val imageUrl: String
) : RecyclerItem

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

        try {
            image.load(item.imageUrl)
        } catch (ex: IllegalArgumentException) {
        }

        bind {
            title.text = item.title
            body.text = item.desc
        }
    }


fun generateHomeItems(): List<HomeItem> {
    return listOf(
        HomeItem(
            1,
            "Первый элемент",
            "Описание первого элемента",
            "https://example.com/image1.jpg"
        ),
        HomeItem(
            2,
            "Второй элемент",
            "Описание второго элемента",
            "https://example.com/image2.jpg"
        ),
        HomeItem(
            3,
            "Третий элемент",
            "Описание третьего элемента",
            "https://example.com/image3.jpg"
        ),
        HomeItem(
            4,
            "Четвертый элемент",
            "Описание четвертого элемента",
            "https://example.com/image4.jpg"
        ),
        HomeItem(5, "Пятый элемент", "Описание пятого элемента", "https://example.com/image5.jpg"),
        HomeItem(
            6,
            "Шестой элемент",
            "Описание шестого элемента",
            "https://example.com/image6.jpg"
        ),
        HomeItem(
            7,
            "Седьмой элемент",
            "Описание седьмого элемента",
            "https://example.com/image7.jpg"
        ),
        HomeItem(
            8,
            "Восьмой элемент",
            "Описание восьмого элемента",
            "https://example.com/image8.jpg"
        ),
    )
}