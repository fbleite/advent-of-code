require_relative "Passwords"
passwords = ARGF.read.split("\n")
puts passwords
# puts passwords.length

puts Passwords.new(passwords).getNumberOfValid_2