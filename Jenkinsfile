def err = null
try {
  
    node {
      
        stage('Preparation') { 
            echo 'Preparation'
        }
      
        stage('Dependencies') {
                
        }
        
        stage('Clean Build') {
                   
        }
        
        stage('Build release ') {
            
        }
      
        stage('Compile') {
                        
        }
    }
  
} catch (caughtError) { 
    
    err = caughtError
    currentBuild.result = "FAILURE"

} finally {
    
    if(currentBuild.result == "FAILURE"){
              sh "echo 'Build FAILURE'"
    }else{
         sh "echo 'Build SUCCESSFUL'"
    }
   
}
