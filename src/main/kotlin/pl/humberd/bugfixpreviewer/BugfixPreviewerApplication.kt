package pl.humberd.bugfixpreviewer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BugfixPreviewerApplication

fun main(args: Array<String>) {
    runApplication<BugfixPreviewerApplication>(*args)
}
