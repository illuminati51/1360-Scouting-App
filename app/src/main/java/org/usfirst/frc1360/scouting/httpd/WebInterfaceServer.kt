package org.usfirst.frc1360.scouting.httpd

import android.content.res.Resources
import fi.iki.elonen.NanoHTTPD

object WebInterfaceServer: NanoHTTPD(1360) {
    override fun serve(session: IHTTPSession?): Response {
        return when (session?.method) {
            Method.GET -> newFixedLengthResponse(Resources.getSystem().assets.open("form.html").bufferedReader().readText())
            Method.POST -> {
                //TODO: Add to database
                newFixedLengthResponse(Resources.getSystem().assets.open("done.html").bufferedReader().readText())
            }
            else -> newFixedLengthResponse(Resources.getSystem().assets.open("505.html").bufferedReader().readText())
        }
    }
}