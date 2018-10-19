package ua.pp.ssenko.report.utils

import kotlin.reflect.KProperty

class Tr(val currentLang: String) {


    inner class tr(
            val ru: String
    ) {

        private val trs = HashMap<String, String>()

        init {
            this.javaClass.fields.forEach {
                trs[it.name] = it.get(this).toString()
            }
        }

        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return trs.getOrDefault(currentLang, property.name)
        }
    }

    val `Sign in from google`: String by tr (
            ru = "Войти через Google"
    )

}
