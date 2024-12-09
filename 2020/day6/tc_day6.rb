require_relative "CustomsDeclaration"
require_relative "CustomsDeclarations"
require "test/unit"
 
class TestSimpleNumber < Test::Unit::TestCase
 
  def test_customs_1
    declarationString = <<-EOF
a
b
c
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(3, declaration.get_number_of_yes)
  end 
  
  
  def test_customs_2
    declarationString = <<-EOF
ab
ac
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(3, declaration.get_number_of_yes)
  end 

  def test_customs_3
    declarationString = <<-EOF
a
a
a
a
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(1, declaration.get_number_of_yes)
  end 

  def test_customs_4
    declarationString = <<-EOF
b
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(1, declaration.get_number_of_yes)
  end 

  
  def test_declarations_1
    declarationsString = <<-EOF
abc

a
b
c

ab
ac

a
a
a
a

b
    EOF

    declaration = CustomsDeclarations.new(declarationsString)
    assert_equal(11, declaration.get_total_yes_answers)
  end 


  def test_customs_0_2
    declarationString = <<-EOF
abc
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(3, declaration.get_number_of_yes_everybody)
  end 
  

  def test_customs_1_2
    declarationString = <<-EOF
a
b
c
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(0, declaration.get_number_of_yes_everybody)
  end 
  
  
  def test_customs_2_2
    declarationString = <<-EOF
ab
ac
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(1, declaration.get_number_of_yes_everybody)
  end 

  def test_customs_3_2
    declarationString = <<-EOF
a
a
a
a
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(1, declaration.get_number_of_yes_everybody)
  end 

  def test_customs_4_2
    declarationString = <<-EOF
b
    EOF

    declaration = CustomsDeclaration.new(declarationString)
    assert_equal(1, declaration.get_number_of_yes_everybody)
  end 

  
  
  def test_declarations_2
    declarationsString = <<-EOF
abc

a
b
c

ab
ac

a
a
a
a

b
    EOF

    declaration = CustomsDeclarations.new(declarationsString)
    assert_equal(6, declaration.get_overlapping_yes_answers)
  end 

end