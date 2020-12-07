package com.home24.assignment

import java.io.File

fun readJson(fileName: String) =
    File("src/test/java/com/home24/assignment/res/${fileName}.json").readText()