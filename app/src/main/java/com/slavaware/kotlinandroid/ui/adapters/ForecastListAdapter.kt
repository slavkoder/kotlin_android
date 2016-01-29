package com.slavaware.kotlinandroid.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.slavaware.kotlinandroid.R
import com.slavaware.kotlinandroid.domain.model.Forecast
import com.slavaware.kotlinandroid.domain.model.ForecastList
import com.slavaware.kotlinandroid.ui.utils.ctx
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.onClick

public class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: ForecastListAdapter.OnItemClickListener)
: RecyclerView.Adapter<ForecastListAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder? {
        val view = parent.ctx.layoutInflater.inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.size()

    class ViewHolder(view: View, val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        private val iconView: ImageView
        private val dateView: TextView
        private val descriptionView: TextView
        private val maxTemperatureView: TextView
        private val minTempreatureView: TextView

        init {
            iconView = view.find(R.id.icon)
            dateView = view.find(R.id.date)
            descriptionView = view.find(R.id.description)
            maxTemperatureView = view.find(R.id.maxTemperature)
            minTempreatureView = view.find(R.id.minTemperature)
        }

        fun bindForecast(forecast: Forecast) {
            with (forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "${high.toString()}"
                minTempreatureView.text = "${low.toString()}"
                itemView.onClick { itemClick(forecast) }
            }
        }
    }

    public interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}
