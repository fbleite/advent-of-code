require_relative "Passport"
class Passports
    def initialize(passportsString)
        raise unless passportsString.is_a?(String)
        cleanPassportString = passportsString.gsub(/\r/, '')
        @passports = cleanPassportString.split("\n\n").map{ 
            |passportString|  Passport.new(passportString)
        }
    end

    def get_number_of_valid
        @passports.select{|passport| passport.is_valid?}.length
    end

    def get_number_of_super_valid
        @passports.select{|passport| passport.is_super_valid?}.length
    end
end