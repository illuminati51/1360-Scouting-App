package org.usfirst.frc1360.scouting

import android.os.Environment
import android.util.Log
import java.util.*
import java.util.function.Predicate

object Storage: ArrayList<Report>() {
    fun checkStorageAvailable() = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

    fun save(done: (Exception) -> Unit = { Log.w("STORAGE_WRITE", it) }) {
        //TODO: save to external storage, and distribute via BlueTooth
    }

    fun refresh(done: (Exception) -> Unit = { Log.w("STORAGE_READ", it) }) {
        //TODO: read from external storage, and pull via BlueTooth
    }

    override fun remove(element: Report): Boolean {
        val result = super.remove(element)
        save()
        return result
    }

    override fun add(element: Report): Boolean {
        val result =  super.add(element)
        save()
        return result
    }

    override fun add(index: Int, element: Report) {
        super.add(index, element)
        save()
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        save()
    }

    override fun removeAll(elements: Collection<Report>): Boolean {
        val result = super.removeAll(elements)
        save()
        return result
    }

    override fun removeIf(filter: Predicate<in Report>?): Boolean {
        val result = super.removeIf(filter)
        save()
        return result
    }

    override fun retainAll(elements: Collection<Report>): Boolean {
        val result = super.retainAll(elements)
        save()
        return result
    }
}