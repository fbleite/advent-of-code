require_relative "Passports"
passportsString = ARGF.read
puts passportsString
passports = Passports.new(passportsString)
puts passports.get_number_of_valid
