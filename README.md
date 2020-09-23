# RecyclerViewCustom

Sebenarnya hampir sama dengan Menampilkan RecyclerView bisa, bedanya itu pada hanya listnya dilakukan Mapper dulu atau diberi tambahan variabel pada Model.

# Contoh List
```kotlin
val response = listOf(
            Goods(
                date = "Sabtu, 29 Agustus 2020",
                title = "Aku Sayang Kamu",
                status = "Selesai",
                alamatDatang = "08:00",
                alamatTujuan = "09:00"
            ),
            Goods(
                date = "Sabtu, 29 Agustus 2020",
                title = "Sayang Kamu Bangat",
                status = "Selesai",
                alamatDatang = "10:00",
                alamatTujuan = "11:00"
            ),
            Goods(
                date = "Sabtu, 30 Agustus 2020",
                title = "Kamu Sayang Aku ?",
                status = "Selesai",
                alamatDatang = "09:00",
                alamatTujuan = "10:00"
            )
        )
```

# Add Data
```kotlin
private var listAsal: MutableList<Goods> = mutableListOf()
.
.
.
listAsal.addAll(response)
```

# Menggunakan Cara Pertama

Pada cara pertama ini kita akan melakukan group list dulu berdasarkan Date
```kotlin
val listGrouping = listAsal.groupBy {
            it.date
        }
```
Setelah melakukan group kita akan melakukan Mapping data ke Model yang baru
```kotlin
data class MappingGoods (
    val date: String? = null,
    val list: List<Goods>? = null
)
```
dan lakukan mapping data
```kotlin
val listMapping: MutableList<MappingGoods> = mutableListOf()
        listGrouping.map {
            listMapping.add(
                MappingGoods(
                    date = it.key,
                    list = it.value
                )
            )
        }
```
Lebih lengkap tetang grouping bisa dilihat disini https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/group-by.html

Lalu list mappinglah yang akan kita tampilkan ke recyclerview. Caranya bisa dilihat di Code

# Menggunakan Cara Kedua

Cara kedua ini kita akan menambahkan satu variabel pada Model Goods, yaitu : 
```kotlin
data class Goods (
    .
    .
    .
    var grouping: String? = null
)
```
Setelah kita menambahkan Variabel baru, kita akan melakukan update nilai ke variabel tersebut, dengan cara 
```kotlin
var visibleData = ""
listAsal.forEach {
            if (visibleData != it.date) {
                visibleData = it.date.toString()
                it.grouping = visibleData
            }
        }
```
Jadi maksud Codingan diatas itu ketika data visibleDate itu tidak sama dengan it.date maka datanya akan di Update, ketika sudah sama maka datanya tidak akan di updatae alias tetap null.

Maka untuk selanjutnya kita bermain di adapter, pada diadapternya ditambahkan percabangan/pengecekan data
```kotlin
if (item.grouping == null) {
                    tvDate.visibility = View.GONE
                }
```
ketika nilai grouping itu null maka View tvDate pada recyclerview akan di hilangkan.

Mungkin itu sedikit penjelasan dari Code, kalau ada yang masih ragu bisa ditanya aja pada issue, nanti kita diskusi.

# Screenshoot

<img src="https://github.com/Egi10/RecyclerViewCustom/blob/master/screenshoot/Screenshot_1600769917.png" width="250">
