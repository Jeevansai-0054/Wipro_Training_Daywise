
enum HttpStatusCode {
  OK = 200,
  NOTFOUND = 404,
  ACCESSDENIED = 403,
  INTERNALERROR = 500
}

// ✅ Example usage
console.log("✅ OK:", HttpStatusCode.OK);              
console.log("❌ Not Found:", HttpStatusCode.NOTFOUND);  
console.log("🚫 Access Denied:", HttpStatusCode.ACCESSDENIED); 
console.log("💥 Internal Error:", HttpStatusCode.INTERNALERROR); 