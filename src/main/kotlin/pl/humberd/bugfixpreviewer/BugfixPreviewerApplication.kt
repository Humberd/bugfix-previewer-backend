package pl.humberd.bugfixpreviewer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class BugfixPreviewerApplication

fun main(args: Array<String>) {
    runApplication<BugfixPreviewerApplication>(*args)
}
