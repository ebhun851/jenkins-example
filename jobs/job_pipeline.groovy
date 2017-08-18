def REMOTE_URL = 'https://github.com/rguruju/jenkins-demo.git'
def BRANCH = 'master'

pipelineJob('hello-Service') {
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
