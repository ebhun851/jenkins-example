def REMOTE_URL = 'https://github.com/zakeeruddin21/spring-music.git'
def BRANCH = 'master'

pipelineJob('spring-music') {
    logRotator(-1, 10)

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url("${REMOTE_URL}")
                    }
                    branch("${BRANCH}")
                    extensions {
                        cleanBeforeCheckout()
                    }
                }
            }
            scriptPath("Jenkinsfile")
        }
    }
}
