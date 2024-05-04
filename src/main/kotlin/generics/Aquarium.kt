package generics

open class WaterSupply(var needProcessing : Boolean)

class TapWater : WaterSupply(true){
    fun addChemicalCleaners(){
        needProcessing = false
    }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true){
    fun filter(){
        needProcessing =false
    }
}
//To not allow passing null, make T of type Any explicitly, by removing the ? after Any.
//In this context, Any is called a generic constraint. It means any type can be passed for T as long as it isn't null.
/*
What you really want is to make sure that only a WaterSupply (or one of its subclasses) can be passed for T.
Replace Any with WaterSupply to define a more specific generic constraint.
* */
class Aquarium<T : WaterSupply>(val waterSupply:T){
    fun addWater() {
        /* The check()function is a standard library function in Kotlin. It acts as an assertion and will throw an IllegalStateException
        if its argument evaluates to false.
         */
        check(!waterSupply.needProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
    }
}


fun genericsExample() {
    val aquarium = Aquarium<TapWater>(TapWater())
    println("water needs processing: ${aquarium.waterSupply.needProcessing}")
    aquarium.waterSupply.addChemicalCleaners()
    println("water needs processing: ${aquarium.waterSupply.needProcessing}")

    val lake_water_aquarium = Aquarium(LakeWater())
    lake_water_aquarium.waterSupply.filter()
    lake_water_aquarium.addWater()
}
fun main(){
    genericsExample()
}