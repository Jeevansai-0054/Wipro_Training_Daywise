<script>
function sumNumbers(...numbers) {
    return numbers.reduce((sum, num) => sum + num, 0);
}

console.log(sumNumbers(1, 2, 3, 4, 5)); 
console.log(sumNumbers(10, 20));      
</script>