package com.example.foodmate_uas.repository

import com.example.foodmate_uas.R
import com.example.foodmate_uas.model.Food

object FoodRepository {
    val allFood = listOf(
        Food(1, "Nasi Goreng Spesial", "Makanan Berat", 22000, 4.8f, "Nasi goreng dengan telur, ayam suwir, dan acar segar.", "#FF6B35", R.drawable.nasi_goreng),
        Food(2, "Ayam Geprek Sambal", "Makanan Berat", 20000, 4.7f, "Ayam crispy digeprek dengan sambal bawang pedas.", "#E4551E", R.drawable.ayam_geprek),
        Food(3, "Mie Ayam Bakso", "Makanan Berat", 18000, 4.6f, "Mie ayam kuah gurih lengkap dengan bakso sapi.", "#2EC4B6", R.drawable.mie_ayam_baso),
        Food(4, "Sate Ayam Madura", "Makanan Berat", 25000, 4.9f, "Sate ayam bumbu kacang khas Madura, 10 tusuk.", "#FF9F1C", R.drawable.sate_ayam),
        Food(5, "Rendang Daging", "Makanan Berat", 28000, 4.9f, "Rendang daging sapi empuk dengan bumbu rempah khas Padang.", "#8B4513", R.drawable.rendang),
        Food(6, "Soto Ayam Lamongan", "Makanan Berat", 17000, 4.6f, "Soto ayam kuah kuning segar dengan koya gurih.", "#FFC107", R.drawable.soto_ayam),
        Food(7, "Gado-Gado Betawi", "Makanan Berat", 16000, 4.5f, "Sayuran segar dengan siraman bumbu kacang khas Betawi.", "#8BC34A", R.drawable.gado_gado),
        Food(8, "Nasi Padang Komplit", "Makanan Berat", 27000, 4.8f, "Nasi dengan lauk pilihan khas Padang, lengkap dan mengenyangkan.", "#D2691E", R.drawable.nasi_padang),
        Food(9, "Bakmi Jawa", "Makanan Berat", 19000, 4.5f, "Mie jawa digoreng dengan bumbu rempah dan telur.", "#A0522D", R.drawable.bakmi_jawa),
        Food(10, "Ikan Bakar Sambal Matah", "Makanan Berat", 26000, 4.7f, "Ikan bakar segar dengan sambal matah khas Bali.", "#FF7043", R.drawable.ikan_bakar),

        Food(11, "Es Teh Manis", "Minuman", 5000, 4.5f, "Teh manis dingin segar, cocok buat cuaca panas.", "#4CC9F0", R.drawable.es_teh),
        Food(12, "Es Jeruk Peras", "Minuman", 8000, 4.6f, "Jeruk peras asli tanpa pengawet.", "#F9C74F", R.drawable.es_jeruk),
        Food(13, "Es Kopi Susu Gula Aren", "Minuman", 15000, 4.8f, "Kopi susu dengan gula aren khas kekinian.", "#6F4E37", R.drawable.es_kopi),
        Food(14, "Jus Alpukat", "Minuman", 12000, 4.6f, "Jus alpukat creamy dengan topping coklat.", "#568203", R.drawable.jus_alpukat),
        Food(15, "Es Cendol", "Minuman", 10000, 4.5f, "Cendol segar dengan santan dan gula merah.", "#8FBC8F", R.drawable.es_cendol),
        Food(16, "Thai Tea", "Minuman", 13000, 4.7f, "Teh thai creamy dengan rasa khas.", "#FF8C69", R.drawable.thai_tea),
        Food(17, "Es Kelapa Muda", "Minuman", 11000, 4.6f, "Kelapa muda segar langsung dari buahnya.", "#87CEEB", R.drawable.es_kelapa),
        Food(18, "Jus Mangga", "Minuman", 12000, 4.5f, "Jus mangga manis dan segar.", "#FFA500", R.drawable.jus_mangga),
        Food(19, "Air Mineral", "Minuman", 4000, 4.3f, "Air mineral dingin kemasan botol.", "#B0E0E6", R.drawable.air_mineral),
        Food(20, "Milkshake Coklat", "Minuman", 16000, 4.7f, "Milkshake coklat creamy dengan whipped cream.", "#5C4033", R.drawable.milkshake_coklat),

        Food(21, "Risoles Mayo", "Camilan", 6000, 4.4f, "Risoles isi ragout ayam dengan topping mayones.", "#F3722C", R.drawable.risoles),
        Food(22, "Pisang Goreng Coklat", "Camilan", 7000, 4.3f, "Pisang goreng crispy dengan lelehan coklat.", "#90BE6D", R.drawable.pisang_goreng),
        Food(23, "Tahu Isi Sayur", "Camilan", 5000, 4.3f, "Tahu goreng isi sayuran segar.", "#DEB887", R.drawable.tahu_isi),
        Food(24, "Cireng Bumbu Rujak", "Camilan", 6000, 4.4f, "Cireng renyah disiram bumbu rujak pedas manis.", "#CD853F", R.drawable.cireng_rujak),
        Food(25, "Batagor Bandung", "Camilan", 12000, 4.6f, "Batagor dengan bumbu kacang khas Bandung.", "#8B7355", R.drawable.batagor),
        Food(26, "Siomay Ikan", "Camilan", 13000, 4.5f, "Siomay ikan dengan bumbu kacang dan kecap.", "#B8860B", R.drawable.siomay),
        Food(27, "Kentang Goreng", "Camilan", 10000, 4.4f, "Kentang goreng renyah dengan saus sambal.", "#DAA520", R.drawable.kentang_goreng),
        Food(28, "Roti Bakar Coklat Keju", "Camilan", 11000, 4.5f, "Roti bakar dengan topping coklat dan keju leleh.", "#D2691E", R.drawable.roti_bakar),
        Food(29, "Cimol Kriuk", "Camilan", 5000, 4.3f, "Cimol renyah dengan bumbu tabur pedas.", "#F4A460", R.drawable.cimol),
        Food(30, "Lumpia Semarang", "Camilan", 9000, 4.5f, "Lumpia isi rebung dan ayam khas Semarang.", "#DEB887", R.drawable.lumpia_semarang),

        Food(31, "Puding Coklat", "Dessert", 10000, 4.6f, "Puding coklat lembut dengan saus vla.", "#4B3621", R.drawable.puding_coklat),
        Food(32, "Es Krim Vanilla", "Dessert", 12000, 4.7f, "Es krim vanilla creamy dengan topping choco chip.", "#FFF8DC", R.drawable.eskrim),
        Food(33, "Brownies Panggang", "Dessert", 15000, 4.8f, "Brownies coklat panggang dengan tekstur fudgy.", "#3B2412", R.drawable.brownies),
        Food(34, "Cheesecake Slice", "Dessert", 18000, 4.9f, "Cheesecake lembut dengan topping berry.", "#FFF0DB", R.drawable.cheesecake),
        Food(35, "Tiramisu Cup", "Dessert", 17000, 4.8f, "Tiramisu klasik dengan lapisan kopi dan mascarpone.", "#6B4423", R.drawable.tiramisu),
        Food(36, "Es Campur", "Dessert", 13000, 4.5f, "Es campur segar dengan berbagai topping manis.", "#FF69B4", R.drawable.es_campur),
        Food(37, "Klepon", "Dessert", 8000, 4.4f, "Klepon isi gula merah dengan taburan kelapa.", "#228B22", R.drawable.klepon),
        Food(38, "Martabak Manis Mini", "Dessert", 14000, 4.7f, "Martabak manis mini dengan topping coklat keju.", "#DAA520", R.drawable.martabak),
        Food(39, "Panna Cotta", "Dessert", 16000, 4.6f, "Panna cotta lembut dengan saus mangga.", "#FFFACD", R.drawable.pannacotta),
        Food(40, "Waffle Madu", "Dessert", 15000, 4.7f, "Waffle renyah disiram madu dan buah segar.", "#F0E68C", R.drawable.waffle),

        Food(41, "Roti Coklat", "Bakery", 8000, 4.5f, "Roti lembut isi coklat melimpah.", "#5C4033", R.drawable.roti_coklat),
        Food(42, "Croissant Butter", "Bakery", 12000, 4.7f, "Croissant renyah dengan aroma butter yang khas.", "#F5DEB3", R.drawable.croissant),
        Food(43, "Donat Gula", "Bakery", 6000, 4.4f, "Donat lembut ditaburi gula halus.", "#FFDAB9", R.drawable.donatgula),
        Food(44, "Roti Sobek Keju", "Bakery", 10000, 4.6f, "Roti sobek lembut dengan taburan keju.", "#FFE4B5", R.drawable.rotikeju),
        Food(45, "Bagel Panggang", "Bakery", 11000, 4.5f, "Bagel panggang dengan tekstur kenyal khas.", "#DEB887", R.drawable.bagel),
        Food(46, "Muffin Blueberry", "Bakery", 13000, 4.6f, "Muffin lembut dengan buah blueberry segar.", "#4B0082", R.drawable.muffin),
        Food(47, "Roti Pisang Coklat", "Bakery", 9000, 4.5f, "Roti isi pisang dan coklat leleh.", "#8B4513", R.drawable.rotipisang),
        Food(48, "Danish Pastry", "Bakery", 14000, 4.7f, "Pastry berlapis dengan isian selai buah.", "#F4A460", R.drawable.danish),
        Food(49, "Roti Tawar Gandum", "Bakery", 12000, 4.4f, "Roti tawar gandum sehat untuk sarapan.", "#D2B48C", R.drawable.rotitawar),
        Food(50, "Cinnamon Roll", "Bakery", 13000, 4.8f, "Roti gulung kayu manis dengan glaze manis.", "#A0522D", R.drawable.cinnamonroll)
    )
    fun getById(id: Int): Food? = allFood.find { it.id == id }
}