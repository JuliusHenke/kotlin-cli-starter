@file:OptIn(ExperimentalFileSystem::class)
package io

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import okio.ExperimentalFileSystem
import okio.FileSystem
import okio.NodeJsFileSystem


actual val fileSystem: FileSystem = NodeJsFileSystem

actual fun findExecutable(executable: String): String =
    executable


actual fun buildHttpClient(): HttpClient =
    HttpClient(Js) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }


/**
 * https://stackoverflow.com/questions/57123836/kotlin-native-execute-command-and-get-the-output
 */
actual fun executeCommandAndCaptureOutput(
    command: List<String>, // "find . -name .git"
    options: ExecuteCommandOptions
): String {
    TODO("executeCommandAndCaptureOutput")
}

actual fun runTest(block: suspend () -> Unit): dynamic =
    GlobalScope.promise { block() }