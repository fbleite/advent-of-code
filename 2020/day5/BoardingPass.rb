class BoardingPass
    def initialize(passString)
        raise unless passString.is_a?(String)
        @passString = passString
        @rowString = passString[0..6]
        @columnString = passString[7..-1]
    end
    
    def get_pass_string
        @passString
    end

    def get_row_number
        binaryString = @rowString.gsub("F", "0").gsub("B", "1")
        get_id(binaryString)
    end

    def get_column_number
        binaryString = @columnString.gsub("L", "0").gsub("R", "1")
        get_id(binaryString)
    end
    def get_seat_id
        (get_row_number * 8) + get_column_number
    end

    private

    def get_id(binaryString)
        binaryString.to_i(2)
    end
end


