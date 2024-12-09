require_relative "Password"
class Passwords

    def initialize(rawPasswords)
      raise unless rawPasswords.is_a?(Array)
      # @passwords = rawPasswords
      @passwords = rawPasswords.map {|rawPassword| Password.new(rawPassword)} 
    end
  
    def getNumberOfValid_1()
      @passwords.select{|password| password.is_valid_1?}.length
    end

    def getNumberOfValid_2()
      @passwords.select{|password| password.is_valid_2?}.length
    end
  
  end
