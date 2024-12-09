require_relative "Passwords"
require "test/unit"
 
class TestSimpleNumber < Test::Unit::TestCase
 
  def test_simple_passwords
    passwords = Array['1-3 a: abcde',
    '1-3 b: cdefg',
    '2-9 c: ccccccccc']
    assert_equal(2, Passwords.new(passwords).getNumberOfValid_1 )
  end

   
  def test_simple_password
    password = '1-3 a: abcde'
    assert_equal(true, Password.new(password).is_valid_1? )
  end
 
     
  def test_simple_password_1_1
    password = '1-3 b: cdefg'
    assert_equal(false, Password.new(password).is_valid_1? )
  end
 

  def test_simple_password_3
    password = '2-9 c: ccccccccc'
    assert_equal(true, Password.new(password).is_valid_1? )
  end 

  def test_simple_password_4
    password = '2-9 c: ccccccccccccc'
    assert_equal(false, Password.new(password).is_valid_1? )
  end

     
  def test_simple_password_1_2
    password = '1-3 a: abcde'
    assert_equal(true, Password.new(password).is_valid_2? )
  end
 
     
  def test_simple_password_2_2
    password = '1-3 b: cdefg'
    assert_equal(false, Password.new(password).is_valid_2? )
  end
 

  def test_simple_password_3_2
    password = '2-9 c: ccccccccc'
    assert_equal(false, Password.new(password).is_valid_2? )
  end 

  def test_simple_password_4_2
    password = '2-9 c: ccccccccccccc'
    assert_equal(false, Password.new(password).is_valid_2? )
  end
end