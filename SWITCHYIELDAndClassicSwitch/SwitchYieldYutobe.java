package SWITCHYIELDAndClassicSwitch;

public class SwitchYieldYutobe {

    public static void main(String[] args) {
        Poet poet = Poet.BAUDELAIRE;

        getPoetUsedSyllables(poet);

    }

    // public static void getPoetUsedSyllables(Poet poet) {
    //     int syllables = switch ( poet ) {
    //         case HUGO:
    //         case VERLAINE:
    //         case RIMBAUD:
    //             System.out.println( "Using the classic alexandrine line." );
    //             yield 12;
    //         case BAUDELAIRE:
    //             System.out.println( "Symbolist rhythm and flow." );
    //             yield 10;
    //         case MALLARME:
    //         case ARAGON:
    //             System.out.println( "Modern style with shorter lines." );
    //             yield 8;
    //         default:
    //             throw new IllegalStateException( "Unknown poet: " + poet );
    //     };
    //     System.out.println( syllables ); 
    // }

    public static void getPoetUsedSyllables(Poet poet) {
        int syllables = switch (poet) {
            case HUGO, VERLAINE, RIMBAUD -> {
                System.out.println("Using the cclassic alexandrine line");
                yield 12;
            }
            case BAUDELAIRE -> {
                System.out.println("Symbolist rhythm and flow");
                yield 10;
            }
            case MALLARME, ARAGON -> {
                System.out.println("");
                yield 8;
            }

            default -> {
                throw new IllegalStateException("Unknown poet: " + poet);
            }
        };
        System.out.println(syllables);
    }
}

enum Poet {
    HUGO,
    VERLAINE,
    RIMBAUD,
    BAUDELAIRE,
    MALLARME,
    ARAGON
}