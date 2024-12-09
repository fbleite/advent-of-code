require_relative "Passport"
require_relative "Passports"
require "test/unit"
 
class TestSimpleNumber < Test::Unit::TestCase
 
  def test_first_passport
    passportString = <<-EOF
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm
EOF
    passport = Passport.new(passportString)
    assert_equal(true, passport.is_valid? )
  end

  def test_second_passport
    passportString = <<-EOF
iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_valid? )
  end

  def test_third_passport
    passportString = <<-EOF
hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm
EOF
    passport = Passport.new(passportString)
    assert_equal(true, passport.is_valid? )
  end

  def test_fourth_passport
    passportString = <<-EOF
hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_valid? )
  end

  def test_all_passports
    passportString = <<-EOF
ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in
EOF
    passports = Passports.new(passportString)
    assert_equal(2, passports.get_number_of_valid )
  end


  def test_super_invalid_passport_1
    passportString = <<-EOF
eyr:1972 cid:100
hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_super_valid? )
  end

  def test_super_invalid_passport_2
    passportString = <<-EOF
iyr:2019
hcl:#602927 eyr:1967 hgt:170cm
ecl:grn pid:012533040 byr:1946
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_super_valid? )
  end

  def test_super_invalid_passport_3
    passportString = <<-EOF
hcl:dab227 iyr:2012
ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277
EOF
    passport = Passport.new(passportString)
    puts 'DUANE'
    assert_equal(false, passport.is_hcl_valid? )
    assert_equal(false, passport.is_super_valid? )
  end\

  def test_super_invalid_passport_4
    passportString = <<-EOF
hgt:59cm ecl:zzz
eyr:2038 hcl:74454a iyr:2023
pid:3556412378 byr:2007
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_super_valid? )
  end

  def test_super_valid_passport_1
    passportString = <<-EOF
pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
hcl:#623a2f
EOF
    passport = Passport.new(passportString)
    assert_equal(true, passport.is_super_valid? )
  end

  def test_super_valid_passport_2
    passportString = <<-EOF
eyr:2029 ecl:blu cid:129 byr:1989
iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
EOF
    passport = Passport.new(passportString)
    assert_equal(true, passport.is_super_valid? )
  end

  def test_super_valid_passport_3
    passportString = <<-EOF
hcl:#888785
hgt:164cm byr:2001 iyr:2015 cid:88
pid:545766238 ecl:hzl
eyr:2022
EOF
    passport = Passport.new(passportString)
    assert_equal(true, passport.is_super_valid? )
  end

  def test_super_valid_passport_4
    passportString = <<-EOF
iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
EOF
    passport = Passport.new(passportString)
    assert_equal(true, passport.is_byr_valid? )
    assert_equal(true, passport.is_hgt_valid? )
    assert_equal(true, passport.is_super_valid? )
  end

  
  def test_super_invalid_passport_fields
    passportString = <<-EOF
hcl:#88878g
hgt:200cm byr:1900 iyr:1900 cid:xx
pid:5457662380 ecl:hzll
eyr:1900
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_hcl_valid? )
    assert_equal(false, passport.is_hgt_valid? )
    assert_equal(false, passport.is_iyr_valid? )
    assert_equal(false, passport.is_byr_valid? )
    assert_equal(true, passport.is_cid_valid? )
    assert_equal(false, passport.is_pid_valid? )
    assert_equal(false, passport.is_ecl_valid? )
    assert_equal(false, passport.is_eyr_valid? )
    assert_equal(false, passport.is_super_valid? )
  end

    
  def test_super_invalid_passport_fields_in
    passportString = <<-EOF
hcl:#88878g
hgt:200in byr:1900 iyr:1900 cid:xx
pid:5457662380 ecl:hzll
eyr:1900
EOF
    passport = Passport.new(passportString)
    assert_equal(false, passport.is_hcl_valid? )
    assert_equal(false, passport.is_hgt_valid? )
    assert_equal(false, passport.is_iyr_valid? )
    assert_equal(false, passport.is_byr_valid? )
    assert_equal(true, passport.is_cid_valid? )
    assert_equal(false, passport.is_pid_valid? )
    assert_equal(false, passport.is_ecl_valid? )
    assert_equal(false, passport.is_eyr_valid? )
    assert_equal(false, passport.is_super_valid? )
  end
end