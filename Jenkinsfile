pipeline {
  agent any
  stages {
    stage('1') {
      steps {
        ws(dir: 'work')
      }
    }

    stage('2') {
      steps {
        archiveArtifacts 'springbootdemo.jar'
      }
    }

  }
}