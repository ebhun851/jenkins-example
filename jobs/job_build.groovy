def REMOTE_URL = 'https://github.com/rguruju/jenkins-demo.git'
def BRANCH = 'master'

job('jobdsl-helloService-CI') {
    logRotator(-1, 10)
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
    triggers {
        githubPush()
    }
    wrappers {
        buildUserVars()
    }
    steps {
        gradle {
            tasks('clean build uploadArchives')
            switches('-P username=${BUILD_USER_ID}')
            switches('-P buildNumber=${BUILD_NUMBER}')
            switches('--stacktrace -i')
            useWrapper(true)
        }
    }
    publishers {
        publishHtml {
            report('build/reports/tests/test') {
                reportName('UnitTestReport')
                reportFiles('index.html')
                keepAll()
                allowMissing()
                alwaysLinkToLastBuild()
            }
        }
    }

    label('vagrant')
}
