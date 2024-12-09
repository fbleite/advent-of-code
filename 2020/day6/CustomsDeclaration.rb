require 'set'
class CustomsDeclaration
    def initialize(customsDeclarationString)
        raise unless customsDeclarationString.is_a?(String)
        @yes_answers = customsDeclarationString.gsub(/\n/, '').chars.to_set
        @individual_yes_answers = customsDeclarationString.split("\n").map{
            |individualDeclaration| individualDeclaration.chars.to_set}
    end

    def get_number_of_yes
        @yes_answers.length
    end
    
    def get_number_of_yes_everybody
        @individual_yes_answers.length
        @individual_yes_answers.reduce(@individual_yes_answers[0]) {
            |all_yes, current_yes| all_yes & current_yes
        }.length
    end
end