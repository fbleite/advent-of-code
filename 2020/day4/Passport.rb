class Passport
    def initialize(passportString)
        raise unless passportString.is_a?(String)
        @passportString = passportString.gsub(/\r/, ' ').gsub(' ', '')
        @fields = {}
        passportString.split.each do |fieldKeyValue|
            splitField = fieldKeyValue.split(":")
            @fields[splitField[0]] = splitField[1]
        end
        @required_fields = Array['byr',
            'iyr',
            'eyr',
            'hgt',
            'hcl',
            'ecl',
            'pid'];
        @optional_field = 'cid'
    end

    def is_valid?
        @required_fields.all? {|field| @passportString.include? field}
    end

    def is_super_valid?
        @required_fields.all? {|field| @passportString.include? field} &&
        is_byr_valid? &&
        is_iyr_valid? &&
        is_eyr_valid? &&
        is_hgt_valid? &&
        is_hcl_valid? &&
        is_ecl_valid? &&
        is_pid_valid? &&
        is_cid_valid?
    end

    def is_byr_valid?
        @fields["byr"].to_i >= 1920 && @fields["byr"].to_i <= 2002
    end

    def is_iyr_valid?
        @fields["iyr"].to_i >= 2010 && @fields["iyr"].to_i <= 2020
    end

    def is_eyr_valid?
        @fields["eyr"].to_i >= 2020 && @fields["eyr"].to_i <= 2030
    end

    def is_hgt_valid?
        retVal = false
        if (/([0-9]+in)|([0-9]+cm)/.match(@fields["hgt"]) == nil)
            false
        end
        unit = @fields["hgt"][-2..-1]
        value = @fields["hgt"][0..-3].to_i
        if unit == 'cm'
            value >= 150 && value <= 193
        elsif unit == 'in'
            value >= 59 && value <= 76
        else
            false
        end
    end

    def is_hcl_valid?
        /#([0-9]|[a-f]){6}/.match(@fields["hcl"]) != nil
    end

    def is_ecl_valid?
        valid_colors = Array['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth']
        valid_colors.include? @fields["ecl"]
    end

    def is_pid_valid?
        @fields["pid"].length == 9
    end

    def is_cid_valid?
        true
    end
end