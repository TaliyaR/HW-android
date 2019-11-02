package com.itis.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: LandmarkAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = LandmarkAdapter(getDataSource()) { Landmark ->
            navigateToDetailsActivity(
                    Landmark.name, Landmark.description, Landmark.image)
        }
        rv_landmarks.adapter = adapter
    }

    private fun navigateToDetailsActivity(name: String, description: String, image: Int) {
        startActivity(DetailsActivity.createIntent(this, name, description, image))
    }


    private fun getDataSource(): List<Landmark> = arrayListOf(
            Landmark("Эйфелева башня", "Металлическая башня в центре Парижа", R.drawable.eiffel_tower),
            Landmark("Колизей", "Амфитеатр, памятник архитектуры Древнего Рима", R.drawable.colosseum),
            Landmark("Великая китайская стена", "Самое крупное сооружение, грандиозный памятник китайской истории,", R.drawable.china),
            Landmark("Чичен-Ица", "Известнейший город народа Майя", R.drawable.chichen_itza),
            Landmark("Мечеть шейха Зайда", "Одна из шести самых больших мечетей в мире", R.drawable.mechet),
            Landmark("Тадж-Махал", "Одна из самых узнаваемых достопримечательностей в Индии", R.drawable.tadzh_mahal),
            Landmark("Ангкор-Ват", "Гигантский индуистский храмовый комплекс в Камбодже", R.drawable.angkor_vat),
            Landmark("Миланский собор", "Третий по величине католический храм планеты", R.drawable.milano_cathedral)
    )
}
