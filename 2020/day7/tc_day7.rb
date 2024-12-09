require_relative "Bags"
require_relative "BagTree"
require "test/unit"
 
class TestSimpleNumber < Test::Unit::TestCase
#   def test_bags_1
#     bagsString = <<-EOF
# light red bags contain 1 bright white bag, 2 muted yellow bags.
# dark orange bags contain 3 bright white bags, 4 muted yellow bags.
# bright white bags contain 1 shiny gold bag.
# muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
# shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
# dark olive bags contain 3 faded blue bags, 4 dotted black bags.
# vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
# faded blue bags contain no other bags.
# dotted black bags contain no other bags.
#     EOF

#     bags = Bags.new(bagsString)
#     assert_equal(6, bags.get_outer_bags_for)
#   end 

def test_simple_bag_1
  bagString = <<-EOF
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
  EOF

  bag = BagTree.new(bagString)
  puts bag
  assert_equal('dark orange', bag.color)
  assert_equal(Array[['3', 'bright white'], ['4','muted yellow']], bag.childBags)
end 

def test_simple_bag_2
  bagString = <<-EOF
light red bags contain 1 bright white bag, 2 muted yellow bags.
  EOF

  bag = BagTree.new(bagString)
  puts bag
  assert_equal('light red', bag.color)
  assert_equal(Array[['1','bright white'], ['2', 'muted yellow']], bag.childBags)
end 

def test_simple_bag_3
  bagString = <<-EOF
faded blue bags contain no other bags.
  EOF

  bag = BagTree.new(bagString)
  puts bag
  assert_equal('faded blue', bag.color)
  assert_equal([], bag.childBags)
end 

end