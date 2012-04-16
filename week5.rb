#Design and Analysis of Algorithms I - Programming Question Week 5
#for each of the 9 target sums x, whether or not x can be formed as the sum of two entries in the given array

output_string = ""
target_sums = [231552, 234756, 596873, 648219, 726312, 981237, 988331, 1277361, 1283379]
hash = {}
IO.readlines("assets/HashInt.txt").collect{|str| i=str.strip.to_i; hash[i] = i}

target_sums.each do |target|
  marker = "0" #initialize to 0 (not found)
  hash.keys.each do |key|
    complement = target-key
    if (hash[complement] != nil)
      #puts "found match: key=" + key.to_s + ", complement=" + complement.to_s
      marker = "1" #we found a match!
      break
    end
  end
  output_string += marker
end
puts output_string
