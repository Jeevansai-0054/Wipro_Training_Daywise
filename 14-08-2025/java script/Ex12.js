function isPalindrome(str) {
  
  const cleaned = str.replace(/\s+/g, "").toLowerCase();
  const reversed = cleaned.split("").reverse().join("");
  return cleaned === reversed;
}


console.log(isPalindrome("madam"));        
console.log(isPalindrome("race car"));      
console.log(isPalindrome("hello"));         