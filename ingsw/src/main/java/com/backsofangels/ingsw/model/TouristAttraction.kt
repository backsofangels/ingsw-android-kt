package com.backsofangels.ingsw.model

class TouristAttraction(id: Long?, name: String?, nation: String?, town: String?, road: String?, houseNumber: String?, phoneNumber: String?, priceRange: Int?, var ageLimit: Int, var peopleLimit: Int, var isFull: Boolean, var services: ArrayList<String>) : Structure(id!!, name!!, nation!!, town!!, road!!, houseNumber!!, phoneNumber!!, priceRange!!)