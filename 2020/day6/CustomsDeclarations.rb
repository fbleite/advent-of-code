require_relative "CustomsDeclaration"

class CustomsDeclarations
    def initialize(customsDeclarationsString)
        raise unless customsDeclarationsString.is_a?(String)
        customsDeclarationsString.gsub!(/\r/, '')
        @declarations = customsDeclarationsString.split("\n\n").map {
            |declarationString| CustomsDeclaration.new(declarationString)
        }
    end

    def get_total_yes_answers
        @declarations.reduce(0) { |sum, declaration| sum + declaration.get_number_of_yes}
    end

    def get_overlapping_yes_answers
        @declarations.reduce(0) { |sum, declaration| sum + declaration.get_number_of_yes_everybody}
    end
end