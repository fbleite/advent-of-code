class Password 
    def initialize(rawPassword)
      raise unless rawPassword.is_a?(String)
      elements = rawPassword.split
      limits = elements[0].split('-')
      @lowerLetterLimit = limits[0].to_i
      @upperLetterLimit = limits[1].to_i
      @letterToBeValidated = elements[1][0..-2]
      @pwd = elements[2]

    end

    def is_valid_1?
        @pwd.count(@letterToBeValidated) <= @upperLetterLimit &&
        @pwd.count(@letterToBeValidated) >= @lowerLetterLimit 
    end

    
    def is_valid_2?
        (@pwd[@upperLetterLimit -1] == @letterToBeValidated) ^
        (@pwd[@lowerLetterLimit -1] == @letterToBeValidated)
    end
end